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

import java.sql.SQLException;

public interface ReplicationGroupManagerMBean {

    public abstract void addSlaveHost(String groupFilter, String host) throws SQLException;

    public abstract void removeSlaveHost(String groupFilter, String host) throws SQLException;

    public abstract void promoteSlaveToMaster(String groupFilter, String host) throws SQLException;

    public abstract void removeMasterHost(String groupFilter, String host) throws SQLException;

    public abstract String getMasterHostsList(String group);

    public abstract String getSlaveHostsList(String group);

    public abstract String getRegisteredConnectionGroups();

    public abstract int getActiveMasterHostCount(String group);

    public abstract int getActiveSlaveHostCount(String group);

    public abstract int getSlavePromotionCount(String group);

    public abstract long getTotalLogicalConnectionCount(String group);

    public abstract long getActiveLogicalConnectionCount(String group);

}
