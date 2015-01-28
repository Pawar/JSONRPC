package com.terzetto.serverrpc;

public enum ERROR {
	SUCCESS{

		@Override
		public int getCode() {
			// TODO Auto-generated method stub
			return 200;
		}

		@Override
		public String getMessage() {
			return "Method Execution success.";
		}
		
	},
	SERVER_ERROR {
		@Override
		public int getCode() {
			return 500;
		}

		@Override
		public String getMessage() {
			return "Server internal error.";
		}
	},
	METHOD_NOT_FOUND {
		@Override
		public int getCode() {
			return 501;
		}

		@Override
		public String getMessage() {
			return "Method not found.";
		}
	},
	OBJECT_NOT_FOUND {
		@Override
		public int getCode() {
			return 502;
		}

		@Override
		public String getMessage() {
			return "Object not found.";
		}
	},
	WRONG_NUMBER_OF_ARGUMENTS {
		@Override
		public int getCode() {
			return 503;
		}

		@Override
		public String getMessage() {
			return "Passing wrong number of arguments to method.";
		}
	},
	ILLEGAL_ARGUMENTS {
		@Override
		public int getCode() {
			return 504;
		}

		@Override
		public String getMessage() {
			return "Argument type mismatch";
		}
	},
	METHOD_EXECUTION_ERROR {
		@Override
		public int getCode() {
			return 505;
		}

		@Override
		public String getMessage() {
			return "Unknown error when invoking method.";
		}
	};
	public abstract int getCode();
	public abstract String getMessage();
}