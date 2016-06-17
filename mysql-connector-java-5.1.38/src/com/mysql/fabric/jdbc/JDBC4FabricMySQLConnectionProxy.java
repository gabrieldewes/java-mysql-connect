/*
  Copyright (c) 2013, 2014, Oracle and/or its affiliates. All rights reserved.

  The MySQL Connector/J is licensed under the terms of the GPLv2
  <http://www.gnu.org/licenses/old-licenses/gpl-2.0.html>, like most MySQL Connectors.
  There are special exceptions to the terms and conditions of the GPLv2 as it is applied to
  this software, see the FOSS License Exception
  <http://www.mysql.com/about/legal/licensing/foss-exception.html>.

  This program is free software; you can redistribute it and/or modify it under the terms
  of the GNU General Public License as published by the Free Software Foundation; version 2
  of the License.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this
  program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth
  Floor, Boston, MA 02110-1301  USA

 */

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Savepoint;
import java.sql.Struct;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimerTask;
import java.util.Timer;
import java.util.TimeZone;
import java.util.concurrent.Executor;

import Buffer;
import CachedResultSetMetaData;
import Connection;
import ConnectionProperties;
import ConnectionPropertiesImpl;
import ExceptionInterceptor;
import Extension;
import Field;
import JDBC4Connection;
import JDBC4ClientInfoProvider;
import JDBC4MySQLConnection;
import MySQLConnection;
import MysqlIO;
import NonRegisteringDriver;
import ResultSetInternalMethods;
import ServerPreparedStatement;
import SingleByteCharsetConverter;
import SQLError;
import StatementImpl;
import StatementInterceptorV2;
import Log;

import com.mysql.fabric.FabricCommunicationException;
import com.mysql.fabric.FabricConnection;
import com.mysql.fabric.Server;
import com.mysql.fabric.ServerGroup;
import com.mysql.fabric.ServerMode;
import com.mysql.fabric.ShardMapping;

/**
 * Limitations:
 * <ul>
 * <li>One shard table can be specified</li>
 * <li>One shard key can be specified</li>
 * </ul>
 */
public class JDBC4FabricMySQLConnectionProxy extends FabricMySQLConnectionProxy implements JDBC4FabricMySQLConnection, FabricMySQLConnectionProperties {

    private FabricConnection fabricConnection;

    public JDBC4FabricMySQLConnectionProxy(Properties props) throws SQLException {
        super(props);
    }

    public Blob createBlob() {
        try {
            transactionBegun();
            return getActiveConnection().createBlob();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Clob createClob() {
        try {
            transactionBegun();
            return getActiveConnection().createClob();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public NClob createNClob() {
        try {
            transactionBegun();
            return getActiveConnection().createNClob();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public SQLXML createSQLXML() throws SQLException {
        transactionBegun();
        return getActiveConnection().createSQLXML();
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        for (Connection c : serverConnections.values())
            c.setClientInfo(properties);
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        for (Connection c : serverConnections.values())
            c.setClientInfo(name, value);
    }

    public java.sql.Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return getActiveConnection().createArrayOf(typeName, elements);
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        transactionBegun();
        return getActiveConnection().createStruct(typeName, attributes);
    }

    public JDBC4ClientInfoProvider getClientInfoProviderImpl() throws SQLException {
        return ((JDBC4MySQLConnection) getActiveConnection()).getClientInfoProviderImpl();
    }
}
