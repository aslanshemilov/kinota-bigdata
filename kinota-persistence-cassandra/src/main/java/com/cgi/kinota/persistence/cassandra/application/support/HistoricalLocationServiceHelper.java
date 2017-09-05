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

package com.cgi.kinota.persistence.cassandra.application.support;

import com.cgi.kinota.commons.application.RelatedEntityManager;

import com.cgi.kinota.persistence.cassandra.domain.HistoricalLocation;
import com.cgi.kinota.persistence.cassandra.infrastructure.persistence.HistoricalLocationRepository;

import com.datastax.driver.core.utils.UUIDs;

import java.util.Date;
import java.util.UUID;

/**
 * Created by bmiles on 7/14/17.
 */
public class HistoricalLocationServiceHelper {
    public static HistoricalLocation createHistoricalLocation(Date time,
                                                              UUID thingUuid,
                                                              UUID locationUuid,
                                                              HistoricalLocationRepository repo,
                                                              RelatedEntityManager related) {
        final HistoricalLocation l = new HistoricalLocation(UUIDs.timeBased(), time);
        repo.save(l);
        assert(thingUuid != null && locationUuid != null);
        related.associateHistoricalLocationWithThing(thingUuid, l.getId());
        related.associateHistoricalLocationWithLocation(locationUuid, l.getId());
        return l;
    }
}