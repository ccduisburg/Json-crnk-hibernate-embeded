package com.codenotfound.crnk.domain.endP;


import io.crnk.core.engine.document.ErrorData;
import io.crnk.core.exception.CrnkMappableException;
import lombok.Getter;

import java.util.Map;

@Getter
public class GlobalError extends CrnkMappableException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final Integer errorCode;
    private final String id;
    private final String title;
    private final String detail;
    private final Map<String, Object> meta;

    /**
     * @param id    Id
     * @param title Titel
     */
    public GlobalError(Integer errorCode, String id, String title) {
        super(errorCode, ErrorData.builder().setTitle(title).setId(id).build());
        this.errorCode = errorCode;
        this.id = id;
        this.title = title;
        this.detail = null;
        this.meta = null;
    }

    public GlobalError(Integer errorCode, String id, String title, String detail) {
        super(errorCode, ErrorData.builder().setTitle(title).setId(id).setDetail(detail).build());
        this.errorCode = errorCode;
        this.id = id;
        this.title = title;
        this.detail = null;
        this.meta = null;
    }

    public GlobalError(Integer errorCode, String id, String title, String detail, Map<String, Object> meta) {
        super(errorCode, ErrorData.builder().setTitle(title).setId(id).setDetail(detail).setMeta(meta).build());
        this.errorCode = errorCode;
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.meta = meta;
    }

    /**
     * @param id    Id
     * @param title Titel
     */
    public GlobalError(String id, String title) {
        this(500, id, title);
    }

    /**
     * @param id Id
     * @param e  Exception
     */
    public GlobalError(String id, Exception e) {
        this(500, id, e.getMessage());
    }
}
