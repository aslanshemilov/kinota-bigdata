/**
 * Kinota (TM) Copyright (C) 2017 CGI Group Inc.
 *
 * Licensed under GNU Lesser General Public License v3.0 (LGPLv3);
 * you may not use this file except in compliance with the License.
 *
 * This software is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * v3.0 as published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License v3.0 for more details.
 *
 * You can receive a copy of the GNU Lesser General Public License
 * from:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.en.html
 *
 */

package com.cgi.kinota.persistence.cassandra.infrastructure.persistence;

import com.cgi.kinota.persistence.cassandra.domain.LocationHistoricalLocation;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

import static com.cgi.kinota.commons.Constants.TABLE_LOCATION_HISTORICAL_LOCATION;

/**
 * Created by bmiles on 2/3/17.
 */
public interface LocationHistoricalLocationRepository extends CassandraRepository<LocationHistoricalLocation> {
    @Query("SELECT * FROM " + TABLE_LOCATION_HISTORICAL_LOCATION + " WHERE locationId = ?0")
    List<LocationHistoricalLocation> findAll(@Param("locationId") UUID locationId);

    @Query("DELETE FROM " + TABLE_LOCATION_HISTORICAL_LOCATION + " WHERE locationId = ?0 AND historicalLocationId = ?1")
    void delete(@Param("locationId") UUID locationId,
                @Param("historicalLocationId") UUID historicalLocationId);
}