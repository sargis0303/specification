package am.aca.specification.service.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class LimitOffsetPageRequest implements Pageable {

    private final int limit;
    private final int offset;
    private final Sort sort;

    public LimitOffsetPageRequest(int limit, int offset) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.sort = Sort.by("id");
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public boolean isPaged() {
        return Pageable.super.isPaged();
    }

    @Override
    public boolean isUnpaged() {
        return Pageable.super.isUnpaged();
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }


    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return Pageable.super.getSortOr(sort);
    }

    @Override
    public Pageable next() {
        return new LimitOffsetPageRequest(getPageNumber() + 1, getPageSize());
    }

    public LimitOffsetPageRequest previous() {
        return getPageNumber() == 0 ? this : new LimitOffsetPageRequest(getPageNumber() - 1, getPageSize());
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new LimitOffsetPageRequest(0, getPageSize());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return offset > 0;
    }

    @Override
    public Optional<Pageable> toOptional() {
        return Pageable.super.toOptional();
    }

}
