package pl.sda.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "charEncoding", urlPatterns = "/*")
public class CharEncodingFilter implements Filter {

    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {

        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(ENCODING);
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding(ENCODING);

        next.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}


