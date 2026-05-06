package com.training.app;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.core.domain.JavaParameter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.library.metrics.ArchitectureMetrics;
import com.tngtech.archunit.library.metrics.ComponentDependencyMetrics;
import com.tngtech.archunit.library.metrics.MetricsComponents;

import jakarta.validation.Valid;

@AnalyzeClasses(packages = "com.training.app")
public class TrainingAppArquitecturaTests {

// Primer test ArchUnit
// Ninguna interfaz acaba en "Impl"
	@ArchTest
	static final ArchRule ninguna_interfaz_acaba_en_impl = noClasses().that().areInterfaces().should()
			.haveSimpleNameEndingWith("Impl").because("Las interfaces no deben acabar por Impl");

//####### REGLAS SOBRE SALIDA ESTANDAR

	/*
	 * Regla para detectar que no haya System.out.println dentro del codigo (se
	 * excluye el paquete de los test)
	 */
	@ArchTest
	static final ArchRule codio_sin_system_out = noClasses().that()
			// Ignoro el paquete de test porque aqui si esta permitido tener system.out
			.resideOutsideOfPackage("..test..").should().accessField(System.class, "out");
	/*
	 * Regla para detectar que no se dejen exception.printStackTrace()
	 */
	@ArchTest
	static final ArchRule codigo_sin_print_stacktrace = noClasses().should().callMethod(Throwable.class,
			"printStackTrace");

//####### REGLAS SOBRE CAPAS

	/*
	 * En una arquitectura hexagonal los imports solo pueden ser de capas inferiores
	 * Si hacemos import de una capa superior estamos rompiendo la arquitectura
	 *
	 */
	@ArchTest
	static final ArchRule codigo_respeta_arquitectura_hexagonal = layeredArchitecture() // Define una estructura por
																						// capas
			.consideringAllDependencies() // Cojo todas las dependencias del proyecto
			.layer("Domain").definedBy("..domain..") // Defino la capa de dominio por todas las clases dentro de
														// ..domain..
			.layer("Application").definedBy("..application..") // Defino la capa de aplicacion por todas las clases
																// dentro de ..application..
			.layer("Adapters").definedBy("..adaptors..") // Defino la capa de adaptadores por todas las clases dentro de
															// ..adaptors..
			.whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "Adapters") // Indico que "imports" de la
																						// capa de dominio pueden estar
																						// en Application y Adapters
			.whereLayer("Application").mayOnlyBeAccessedByLayers("Adapters")// Indico que "imports" de la capa de
																			// adaptadores pueden estar en Application
																			// (pero no en dominio)
			.whereLayer("Adapters").mayNotBeAccessedByAnyLayer(); // Ninguna capa debe tener import de adapters
	
	
	@ArchTest
	static final ArchRule handler_anotado_por_component = classes().that().haveSimpleNameEndingWith("Handler").should()
			.beAnnotatedWith(org.springframework.stereotype.Component.class).allowEmptyShould(true)
			.because("Un handler debe ser manejado por SpringBoot como Component");

//####### REGLAS SOBRE NOMENCLATURA

	// Definimos un predicado para las funciones "and" de archunit
	// Importante: Tiene que definirse ANTES de su uso
	private static final DescribedPredicate<JavaClass> IMPLEMENTA_ALGUNA_INTERFAZ = new DescribedPredicate<>(
			"implementa al menos una interfaz") {
		@Override
		public boolean test(JavaClass javaClass) {
			return !javaClass.getInterfaces().isEmpty();
		}
	};

	@ArchTest
	static final ArchRule implementaciones_de_interfaces_acaban_en_impl = classes().that().areNotInterfaces().and()
			.areNotEnums().and(IMPLEMENTA_ALGUNA_INTERFAZ).should().haveSimpleNameEndingWith("Impl");

	@ArchTest
	static final ArchRule repositorios_acaban_en_repository = classes().that().resideInAPackage("..repository..")
			.should().haveSimpleNameEndingWith("Repository").allowEmptyShould(true);

	@ArchTest
	static final ArchRule dto_acaban_en_dto = classes().that()
			// Descarto los enumerados que no deben acabar por DTO
			.areNotEnums().and().resideInAPackage("..dto..").should().haveSimpleNameEndingWith("DTO")
			.allowEmptyShould(true);
	@ArchTest
	static final ArchRule entidades_acaban_en_entity = classes().that()
			// Descarto los enumerados que no deben acabar por DTO
			.areNotEnums().and().resideInAPackage("..entity..").should().haveSimpleNameEndingWith("Entity")
			.allowEmptyShould(true);

	@ArchTest
	static final ArchRule interfaces_no_acaban_en_impl = classes().that().areInterfaces().should()
			.haveSimpleNameNotEndingWith("Impl").allowEmptyShould(true);

//####### REGLAS SOBRE EL USO DE SPRINGBOOT

	@ArchTest
	static final ArchRule restcontroller_shold_be_on_adaptors_layer = classes().that()
			.areAnnotatedWith(RestController.class).should().resideInAPackage("..adaptors..");

	@ArchTest
	static final ArchRule servicecontroller_shold_be_on_adaptors_layer = classes().that()
			.areAnnotatedWith(Service.class).should().resideInAPackage("..adaptors..").allowEmptyShould(true);

	@ArchTest
	static final ArchRule sin_inyeccion_autowired =
			// Detecta @Autowired, @Inject y @Resource sobre campos
			NO_CLASSES_SHOULD_USE_FIELD_INJECTION
					.because("Usa inyeccion por constructor: inmutable y testeable. No usar @Autowired");

//##### REGLAS DE VALIDACION

	// Creo una nueva condicion de comprobacion porque ArchUnit por defecto no tiene
	// la que necesito
	private static final ArchCondition<JavaMethod> METODO_REST_VALIDA_PARAMETROS = new ArchCondition<>(
			"Rest debe tener @Valid o @Validated en parámetros @RequestBody") {

		@Override
		public void check(JavaMethod metodo, ConditionEvents events) {
			boolean validaParametro = false;
			for (JavaParameter parametro : metodo.getParameters()) {
				//Compruebo si el metodo tiene RequestBody (JSON)
				if (parametro.isAnnotatedWith(RequestBody.class)) {
					validaParametro = parametro.isAnnotatedWith(Valid.class)
							|| parametro.isAnnotatedWith(Validated.class)
							|| metodo.getOwner().isAnnotatedWith(Validated.class);

					if (!validaParametro) {
						String message = String.format("El método %s tiene un @RequestBody sin validación",
								metodo.getFullName());
						//Aniado un evento de violacion
						events.add(SimpleConditionEvent.violated(metodo, message));
					}
				}
			}
		}
	};

	@ArchTest
	static final ArchRule api_rest_debe_validar_datos_entrada = methods().that().areDeclaredInClassesThat()
			.areAnnotatedWith(RestController.class).and().arePublic().should(METODO_REST_VALIDA_PARAMETROS);
	
	

}
