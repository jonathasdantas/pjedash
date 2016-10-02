package br.jus.pjedash.servico;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharEncodingUTF8Filter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

	public void destroy() {
		// nada a fazer
	}

	public void init(FilterConfig fConfig) throws ServletException {
		fConfig.getServletContext().log("CharEncodingUTF8Filter inicializado");
	}
	
}