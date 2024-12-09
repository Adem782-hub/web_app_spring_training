package fr.lernejo.todo;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component // Cette annotation permet à Spring de gérer ce filtre
@WebFilter("/*") // Applique le filtre à toutes les requêtes
public class ApplicationIdentifierFilter implements Filter {

    private final String instanceId;

    public ApplicationIdentifierFilter() {
        this.instanceId = UUID.randomUUID().toString();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse httpResponse) {
            httpResponse.setHeader("Instance-Id", instanceId);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

