/*
 * [The "BSD licence"]
 * Copyright (c) 2012 Dandelion
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of Dandelion nor the names of its contributors 
 * may be used to endorse or promote products derived from this software 
 * without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.dandelion.datatables.core.extension.feature;

import java.util.HashSet;
import java.util.Set;

import com.github.dandelion.datatables.core.exception.BadConfigurationException;
import com.github.dandelion.datatables.core.html.HtmlColumn;
import com.github.dandelion.datatables.core.html.HtmlTable;
import com.github.dandelion.datatables.core.util.ResourceHelper;

/**
 * <p>
 * Feature used in accordance with the <code>sortType</code> column attribute to
 * help DataTables to configure the sort on a column.
 * <p>
 * See http://datatables.net/plug-ins/sorting for an overview of all available
 * sorting functions.
 * 
 * @author Thibault Duchateau
 * @since 0.9.0
 */
public class SortingFeature extends AbstractFeature {

	@Override
	public String getName() {
		return "SortingFeature";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public void setup(HtmlTable table) throws BadConfigurationException {
		Set<SortType> enabledSortTypes = new HashSet<SortType>();
		
		for (HtmlColumn column : table.getLastHeaderRow().getColumns()) {
			if (column.getSortType() != null) {
				enabledSortTypes.add(column.getSortType());
			}
		}
		
		String content = null;
		for(SortType sortType : enabledSortTypes){
			switch (sortType) {
			case DATE:
				content = ResourceHelper.getFileContentFromClasspath("datatables/features/sorting/date-uk.js");
				break;
			case NATURAL:
				content = ResourceHelper.getFileContentFromClasspath("datatables/features/sorting/naturalSort.js");
				break;
			case ALT_STRING:
				content = ResourceHelper.getFileContentFromClasspath("datatables/features/sorting/alt-string.js");
				break;
			case ANTI_THE:
				content = ResourceHelper.getFileContentFromClasspath("datatables/features/sorting/anti-the.js");
				break;
			case CURRENCY:
				content = ResourceHelper.getFileContentFromClasspath("datatables/features/sorting/currency.js");
				break;
			case FILESIZE:
				content = ResourceHelper.getFileContentFromClasspath("datatables/features/sorting/filesize.js");
				break;
			case FORMATTED_NUMBERS:
				content = ResourceHelper.getFileContentFromClasspath("datatables/features/sorting/formatted-numbers.js");
				break;
			default:
				break;
			}
			appendToBeforeAll(content);
		}
	}
}