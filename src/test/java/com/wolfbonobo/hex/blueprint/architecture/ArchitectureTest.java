package com.wolfbonobo.hex.blueprint.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

class ArchitectureTest {

    private static final String BASE = "com.wolfbonobo.hex.blueprint";

    private static final String DOMAIN = BASE + ".domain..";
    private static final String APPLICATION = BASE + ".application..";
    private static final String INFRA = BASE + ".infrastructure..";

    private static final String PORTS_IN = BASE + ".application.ports.in..";
    private static final String PORTS_OUT = BASE + ".application.ports.out..";

    private static final String ADAPTERS_IN = BASE + ".infrastructure.adapters.in..";
    private static final String ADAPTERS_OUT = BASE + ".infrastructure.adapters.out..";

    private final JavaClasses classes =
            new ClassFileImporter()
                    .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                    .importPackages(BASE);

    @Test
    void layered_architecture_is_respected() {
        // Optional layers: do not fail if a layer/package is still empty (blueprint stage)
        ArchRule rule = Architectures.layeredArchitecture()
                .consideringAllDependencies()
                .withOptionalLayers(true)

                .layer("Domain").definedBy(DOMAIN)
                .layer("Application").definedBy(APPLICATION)
                .layer("Infrastructure").definedBy(INFRA)

                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "Infrastructure")
                .whereLayer("Application").mayOnlyBeAccessedByLayers("Infrastructure")
                .whereLayer("Infrastructure").mayNotBeAccessedByAnyLayer();

        rule.check(classes);
    }

    @Test
    void domain_must_not_depend_on_application_or_infrastructure() {
        noClasses()
                .that().resideInAPackage(DOMAIN)
                .should().dependOnClassesThat()
                .resideInAnyPackage(APPLICATION, INFRA)
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void domain_must_not_use_spring() {
        noClasses()
                .that().resideInAPackage(DOMAIN)
                .should().dependOnClassesThat()
                .resideInAnyPackage("org.springframework..")
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void application_must_not_depend_on_infrastructure() {
        noClasses()
                .that().resideInAPackage(APPLICATION)
                .should().dependOnClassesThat()
                .resideInAPackage(INFRA)
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void ports_must_be_interfaces() {
        classes()
                .that().resideInAnyPackage(PORTS_IN, PORTS_OUT)
                .should().beInterfaces()
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void usecase_interfaces_must_live_in_ports_in() {
        classes()
                .that().haveSimpleNameEndingWith("UseCase")
                .should().resideInAPackage(PORTS_IN)
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void usecase_implementations_must_live_in_application_usecase_packages() {
        classes()
                .that().haveSimpleNameEndingWith("UseCaseImpl")
                .should().resideInAnyPackage(
                        BASE + ".application.command.usecase..",
                        BASE + ".application.query.usecase.."
                )
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void adapters_in_must_not_depend_on_usecase_implementations() {
        noClasses()
                .that().resideInAPackage(ADAPTERS_IN)
                .should().dependOnClassesThat()
                .resideInAnyPackage(
                        BASE + ".application.command.usecase..",
                        BASE + ".application.query.usecase.."
                )
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void adapters_out_must_depend_on_ports_out() {
        classes()
                .that().resideInAPackage(ADAPTERS_OUT + "adapter..")
                .should().dependOnClassesThat()
                .resideInAPackage(PORTS_OUT)
                .allowEmptyShould(true)
                .check(classes);
    }
}
