package com.terzetto.serverrpc;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import com.terzetto.typeparser.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RPC extends HttpServlet {
	// static Logger log = Logging.getLoger(RPCServer.class);;

	public RPC() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String call = request.getParameter("call");
		// System.out.println(call);
		Request req = Request.convert(call);
		String methodName = req.getMethod();
		String objectName = req.getObject();
		try {
			Object obj = UserClass.classobject.get(objectName);
			Class cls = (Class) UserClass.classname.get(objectName);
			if (obj != null && cls != null) {
				Method method = getMethod(cls, methodName);
				if (method != null) {
					// TODO Check return values is a array and create JSON
					int size = req.getParams().length;
					if (size == 0) {
						if (method.getReturnType().isArray()) {
							Object[] returnValue = (Object[]) method.invoke(obj);
							out.println(Response.convert(new Response(
									ERROR.SUCCESS.getCode(), ERROR.SUCCESS
											.getMessage(), returnValue)));
						} else {
							Object returnValue = method.invoke(obj);
							out.println(Response.convert(new Response(
									ERROR.SUCCESS.getCode(), ERROR.SUCCESS
											.getMessage(), returnValue)));
						}
					} else {
						Object[] params = new Object[size];
						Class parameter[] = method.getParameterTypes();
						for (int i = 0; i < size; i++) {
							try {
								params[i] = StringToTypeParser.parse(
										req.getParams()[i].toString(),
										parameter[i]);
							} catch (IllegalArgumentException e) {
								params[i] = req.getParams()[i];
							}
						}

						try {
							if (method.getReturnType().isArray()) {
								String re = Response.convert(new Response(
										ERROR.SUCCESS.getCode(), ERROR.SUCCESS
										.getMessage(),  method.invoke(obj, params)));
							//	System.out.println(re);
								out.println(re);
							} else {
								Object returnValue = method.invoke(obj, params);
								out.println(Response.convert(new Response(
										ERROR.SUCCESS.getCode(), ERROR.SUCCESS
												.getMessage(), returnValue)));
							}							
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
							out.print(Response.convert(new Response(
									ERROR.ILLEGAL_ARGUMENTS.getCode(),
									ERROR.ILLEGAL_ARGUMENTS.getMessage(),
									ERROR.ILLEGAL_ARGUMENTS.getMessage())));
						} catch (Exception e) {
							e.printStackTrace();
							out.print(Response.convert(new Response(
									ERROR.METHOD_EXECUTION_ERROR.getCode(),
									ERROR.METHOD_EXECUTION_ERROR.getMessage(),
									ERROR.METHOD_EXECUTION_ERROR.getMessage())));
						}
					}
				} else {
					out.print(Response.convert(new Response(
							ERROR.METHOD_NOT_FOUND.getCode(),
							ERROR.METHOD_NOT_FOUND.getMessage(),
							ERROR.METHOD_NOT_FOUND.getMessage())));
				}
			} else {
				out.print(Response.convert(new Response(ERROR.OBJECT_NOT_FOUND
						.getCode(), ERROR.OBJECT_NOT_FOUND.getMessage(),
						ERROR.OBJECT_NOT_FOUND.getMessage())));
			}
		} catch (Exception e) {
			// log.log(Level.WARN, e.getMessage(), e);
			e.printStackTrace();
			out.print(Response.convert(new Response(ERROR.SERVER_ERROR
					.getCode(), ERROR.SERVER_ERROR.getMessage(),
					ERROR.SERVER_ERROR.getMessage())));
		}

	}

	@SuppressWarnings("rawtypes")
	private static Method getMethod(Class cls, String method) throws Exception {
		Method[] methods = cls.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(method))
				return methods[i];
		}
		return null;
	}

	@SuppressWarnings("unused")
	private static Object[] getParameters(HttpServletRequest request) {
		int size = request.getParameterMap().size();
		Object[] params = new Object[size - 2];
		for (int i = 0; i < request.getParameterMap().size(); i++) {
			if (request.getParameter("p" + i) != null)
				params[i] = (Object) request.getParameter("p" + i);
		}
		return params;
	}
}