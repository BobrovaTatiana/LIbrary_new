package ru.bobrova.controllers.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Tanusha on 01/03/2017.
 */
@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {
    private static Logger logger = Logger.getLogger(AuthFilter.class);

    private FilterConfig filterConfig;

    @Override
    public void destroy() {
        filterConfig = null;
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        filterConfig = fConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
            HttpServletRequest req = (HttpServletRequest) request;
            // Раскладываем адрес на составляющие
            logger.trace("doFilter is execute");

            String[] list = req.getRequestURI().split("/");
            // Извлекаем наименование страницы
            String page = null;
            if (list[list.length - 1].length() > 0) {
                page = list[list.length - 1];
            }
            // Если открывается главная страница, то выполняем проверку
            if ((page != null) && page.equalsIgnoreCase("list")) {
                if (req.getSession().getAttribute("id") != null) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    // Перенаправление на страницу login.jsp
                    ServletContext ctx = filterConfig.getServletContext();
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            } else if (page != null) {
                // Добавляем страницу в список
                //if (!pages.contains(page))
                   // pages.add(page);
            }
        }
        filterChain.doFilter(request, response);
    }
}
