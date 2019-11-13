package com.api.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * Définit le mapper pour la sérialisation/désérialisation avec Jersey
 * Il faut ajouter ce provider à l'app Jersey:
 * register(JacksonMapper.class)
 * <p>
 * Si besoin d'utiliser un mapper différent de celui du framework ne pas utiliser cette classe
 * mais en créer une nouvelle au niveau du projet api
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonMapper extends JacksonJaxbJsonProvider {
	public JacksonMapper() {
		super();
		ObjectMapper mapper = new ObjectMapper()
			// Set of support modules for Java 8 datatypes (Optionals, date/time) and features (parameter names)
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false) //ISODate
			//				.setDateFormat(new SimpleDateFormat(DateCalculs.FORMAT_ISO_8601_WITHOUT_TZ))
			//				.registerModule(new ParameterNamesModule())
			//				.registerModule(new Jdk8Module())
			//				.registerModule(new JavaTimeModule())

			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.enable(SerializationFeature.INDENT_OUTPUT)
			.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, false)
			.setDefaultPropertyInclusion(JsonInclude.Value.construct(JsonInclude.Include.ALWAYS, JsonInclude.Include.NON_NULL));


		mapper.setVisibility(
			mapper.getSerializationConfig().getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
				.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withSetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withCreatorVisibility(JsonAutoDetect.Visibility.NONE)
		);
		setMapper(mapper);
	}
}
