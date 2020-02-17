package com.ernest.skynett;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ernest.skynett");

        noClasses()
            .that()
                .resideInAnyPackage("com.ernest.skynett.service..")
            .or()
                .resideInAnyPackage("com.ernest.skynett.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.ernest.skynett.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
