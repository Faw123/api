package br.com.api.bean;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.dao.CadastrarCliente;
import br.com.api.model.Cliente;

/**
 * Servlet implementation class Cadastrar
 */
@WebServlet("/cadastrar")
public class Cadastrar extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			/* report an error */ }

		try {
			// Pega json
			JSONObject jsonObject = HTTP.toJSONObject(jb.toString());
			ObjectMapper mapper = new ObjectMapper();
			Cliente cliente = mapper.readValue(jb.toString(), Cliente.class);
			CadastrarCliente cad = new CadastrarCliente();
			cad.salvar(cliente);
			System.out.println(cliente.getNome());
			System.out.println(cliente.getEmail());
		} catch (JSONException e) {
			// crash and burn
			throw new IOException("Error parsing JSON request string");
		}

		
	
		doGet(request, response);
	}
}
