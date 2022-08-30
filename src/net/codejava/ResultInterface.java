package net.codejava;

import java.sql.ResultSet;
import java.util.List;

public interface ResultInterface<T> {
	public List<T> recupResult(String modele);
	public void setResultSet(ResultSet resultSet);
}
