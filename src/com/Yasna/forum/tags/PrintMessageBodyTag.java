/**
 * Copyright (C) 2001 Yasna.com. All rights reserved.
 *
 * ===================================================================
 * The Apache Software License, Version 1.1
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        Yasna.com (http://www.yasna.com)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Yazd" and "Yasna.com" must not be used to
 *    endorse or promote products derived from this software without
 *    prior written permission. For written permission, please
 *    contact yazd@yasna.com.
 *
 * 5. Products derived from this software may not be called "Yazd",
 *    nor may "Yazd" appear in their name, without prior written
 *    permission of Yasna.com.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL YASNA.COM OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of Yasna.com. For more information
 * on Yasna.com, please see <http://www.yasna.com>.
 */

package com.Yasna.forum.tags;

import javax.servlet.jsp.tagext.*;
import com.Yasna.forum.*;
import javax.servlet.jsp.*;
import java.io.*;

/**
 * Jsp tag must be nested in message tag. Prints the body content if
 * user is authorized to read the content or error message otherwise.
 */
public class PrintMessageBodyTag extends TagSupport {

    private String resultId = null;

    public int doStartTag() throws JspException {
        try {
            MessageTag mt = getMessageTag();
            String body = mt.getBody();
            if (body == null) {
                body = "[You are not authorized to read this message!]";
            }
            pageContext.getOut().print(body);
        } catch(Exception e) {
        }
        return SKIP_BODY;
    }

    /**
     * Retrieve Authorization object from pageContext which give the information
     * for currently logged user.
     *
     * @return Authorization object.
     */
    private Authorization getAuthToken() {
        YazdState js = (YazdState) pageContext.getAttribute("yazdUserState",PageContext.SESSION_SCOPE);
		return js.getAuthorization();
    }

    /**
     * Gets MessageTag object which represents the parent Message tag.
     * @return MessageTag object
     */
    private MessageTag getMessageTag() {
        MessageTag mt = null;
        try {
            mt = (MessageTag)this.findAncestorWithClass(this,
                Class.forName("com.Yasna.forum.tags.MessageTag"));
        } catch(Exception e) {
        }
        return mt;
    }

    public void setId(String id) {
    	resultId = id;
    }
}