package fossilsarcheology.server.util;

public enum ReleaseType {
	DEVELOP {
		@Override
		public String getBranding() {
			return "DEV";
		}

		@Override
		public boolean enableDebugging() {
			return true;
		}
	},
	RELEASE_CANDIDATE {
		@Override
		public String getBranding() {
			return "RC";
		}
	},
	RELEASE {
		@Override
		public String getBranding() {
			return "";
		}
	};

	public static ReleaseType parseVersion(String version) {
		if (version.endsWith("-develop")) {
			return DEVELOP;
		} else if (version.contains("-rc")) {
			return RELEASE_CANDIDATE;
		} else {
			return RELEASE;
		}
	}

	public abstract String getBranding();

	public boolean enableDebugging() {
		return false;
	}
}
