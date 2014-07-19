package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class SetCharacterEncodingFilter
 */
public class SetCharacterEncodingFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SetCharacterEncodingFilter() {
	}

	protected String encoding = null;
	protected FilterConfig filterConfig = null;
	protected boolean ignore = true;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		if (ignore || request.getCharacterEncoding() == null) {
			encoding = selectEncoding(request);
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	protected String selectEncoding(ServletRequest request) {
		return this.encoding;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null) {
			ignore = true;
		} else if (value.equalsIgnoreCase("true")) {
			ignore = true;
		} else if (value.equalsIgnoreCase("yes")) {
			ignore = true;
		} else {
			ignore = false;
		}
	}

}
