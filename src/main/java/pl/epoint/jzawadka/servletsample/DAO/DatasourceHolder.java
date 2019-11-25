package pl.epoint.jzawadka.servletsample.DAO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DatasourceHolder {

    private BaseDAO baseDAO = ProductPostgresDAO.getInstance();

    public BaseDAO getDatasource() {
        return baseDAO;

    }

}
