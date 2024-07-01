package com.example.application.proxies;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.domains.contracts.proxies.CalculatorProxy;
import com.example.webservice.schema.AddRequest;
import com.example.webservice.schema.AddResponse;
import com.example.webservice.schema.MultiplyRequest;
import com.example.webservice.schema.MultiplyResponse;
import com.example.webservice.schema.SubtractRequest;
import com.example.webservice.schema.SubtractResponse;

public class CalculatorProxyImpl extends WebServiceGatewaySupport implements CalculatorProxy {
	
	private static final int SCALE = 14;
	
	public double add(double a, double b) {
		var request = new AddRequest();
		request.setOp1(a);
		request.setOp2(b);
		var response = (AddResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://example.com/webservices/schemas/calculator"));
		return round(response.getAddResult());
	}

	public double subtract(double a, double b) {
		var request = new SubtractRequest();
		request.setOp1(a);
		request.setOp2(b);
		var response = (SubtractResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://example.com/webservices/schemas/calculator"));
		return round(response.getSubtractResult());
	}
	
	public double multiply(double a, double b) {
		var request = new MultiplyRequest();
		request.setOp1(a);
		request.setOp2(b);
		var response = (MultiplyResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://example.com/webservices/schemas/calculator"));
		return round(response.getMultiplyResult());
	}
	
	private double round(double value) {
		return BigDecimal.valueOf(value).setScale(SCALE, RoundingMode.HALF_UP).doubleValue();
	}
}