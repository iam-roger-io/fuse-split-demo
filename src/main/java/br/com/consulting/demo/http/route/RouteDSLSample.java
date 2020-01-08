package br.com.consulting.demo.http.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.ExpressionBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;


/**
 * 
 * @author rosantos
 *
 */
@Component
public class RouteDSLSample extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {

		from("file://{{custom.folder.in}}?fileName=url.txt&delete=true").routeId("route-example1")
	  
		.setHeader(Exchange.HTTP_METHOD).constant(HttpMethod.POST)
         .setHeader(Exchange.CONTENT_TYPE).constant("application/json")
         
         /*
          * o split é para processar cada linha em um body separado
          * Por default esse processamento é sequencial mas pode ser melhorado (paralelizado) utilizando o método parallelProcessing. 
          * O Default do parallelProcessing  são 10 threads
          */
         .split().tokenize("\n").parallelProcessing() 
         
         .log(" Invoking URL ${body}")
         
         /*
          * Para forçar a utilização do componente camel-http4 o https precisa ser trocado por https4
          * Este exemplo foi pensado em https somente mas pode ser contruido um processor para melorar isso.
          */
         .setBody(ExpressionBuilder.regexReplaceAll(body(), "https", "https4"))

         //Invoca o web service
         .toD("${body}")         
		 
         //Imprime a resposta do serviço
         .log("Response of service: ${body}");
		
		
	}
}