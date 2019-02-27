package com.serverless;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.db.TrainingRepository;
import com.serverless.db.model.Training;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetTrainings implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
    private static final Logger LOG = LogManager.getLogger(GetTrainings.class);

	private static final TrainingRepository repository = new TrainingRepository();

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		final List<Training> trainings = repository.getTrainings();
		final ApiGatewayResponse response = ApiGatewayResponse.builder()
			.setStatusCode(200)
			.setObjectBody(trainings)
			.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
			.build();

		LOG.debug(String.format("trainings retrieved: \n%s", response.getBody()));
		
		return response; 
	}
}
