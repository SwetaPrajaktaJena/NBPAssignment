package pl.parser.npb.Services;

import java.util.List;

public interface ServiceConnection {
	public List<String> getYearlyFileNames(String Year);
	public String getxmlContent(String fileName);
}
