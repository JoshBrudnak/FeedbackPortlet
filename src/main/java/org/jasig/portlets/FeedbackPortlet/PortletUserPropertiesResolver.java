/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.portlets.FeedbackPortlet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;

/**
 * PortletUserPropertiesResolver get the properties for a user from the 
 * portlet's userinfo map. Keys are defined for each user property that allow
 * the default userinfo property name to be overridden.  If the key value is
 * null for any given property, that property won't be retrieved or recorded.
 * 
 * @author Jen Bourey
 */
public class PortletUserPropertiesResolver {

	private String nameKey = "user.name.full";
	private List<String> userRoles = new ArrayList<String>();
	private String idKey = "user.login.id";
	private String emailKey = "mail";

	/**
	 * Retrieve the properties for the user.
	 * 
	 * @param request
	 * @return
	 */
	public UserProperties getProperties(PortletRequest request) {

		// get the userinfo map from the portlet request
		Map<String, String> userinfo = (Map<String, String>) request.getAttribute("javax.portlet.userinfo");
		
		// construct a new properties object
		UserProperties props = new UserProperties();
		
		// get the user's role
		for (String role : userRoles) {
			if (request.isUserInRole(role)) {
				props.setUserrole(role);
				break;
			}
		}

		// get the user's full name
		if (nameKey != null)
			props.setUsername((String) userinfo.get(nameKey));

		// get the user's unique id
		if (idKey != null)
			props.setUserid((String) userinfo.get(idKey));

		// get the user's email address
		if (emailKey != null)
			props.setUseremail((String) userinfo.get(emailKey));

		return props;
	}

	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}
	
	public void setUserRoles(List<String> roles) {
		this.userRoles = roles;
	}

	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}

	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}

}


/*
 * PortletUserPropertiesResolver.java
 * 
 * Copyright (c) Feb 5, 2008 Yale University. All rights reserved.
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE, ARE EXPRESSLY DISCLAIMED. IN NO EVENT SHALL
 * YALE UNIVERSITY OR ITS EMPLOYEES BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED, THE COSTS OF PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED IN ADVANCE OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Redistribution and use of this software in source or binary forms, with or
 * without modification, are permitted, provided that the following conditions
 * are met.
 * 
 * 1. Any redistribution must include the above copyright notice and disclaimer
 * and this list of conditions in any related documentation and, if feasible, in
 * the redistributed software.
 * 
 * 2. Any redistribution must include the acknowledgment, "This product includes
 * software developed by Yale University," in any related documentation and, if
 * feasible, in the redistributed software.
 * 
 * 3. The names "Yale" and "Yale University" must not be used to endorse or
 * promote products derived from this software.
 */