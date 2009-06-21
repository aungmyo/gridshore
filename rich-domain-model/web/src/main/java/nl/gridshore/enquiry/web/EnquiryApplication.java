/*
 * Copyright (c) 2009. Gridshore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.gridshore.enquiry.web;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class EnquiryApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return EnquiryHomePage.class;
    }

    @Override
    protected void init() {
        addComponentInstantiationListener(new SpringComponentInjector(this));

        boolean inProduction = Application.DEPLOYMENT.equals(getConfigurationType());

        getMarkupSettings().setStripComments(inProduction);
        getMarkupSettings().setStripWicketTags(true);
        getMarkupSettings().setStripXmlDeclarationFromOutput(true);

        //getResourceSettings().setStripJavascriptCommentsAndWhitespace(inProduction);

        if (inProduction) {
            //getApplicationSettings().setPageExpiredErrorPage(ExpiredPage.class);
            //getApplicationSettings().setInternalErrorPage(ErrorPage.class);
            getExceptionSettings()
                    .setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
        }
    }
}
