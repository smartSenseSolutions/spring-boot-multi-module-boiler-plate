package ss.mod.demo.api.model.response;


import com.smartsensesolutions.java.commons.sort.Sort;
import lombok.*;
import org.springframework.data.domain.Page;
import ss.mod.demo.api.model.request.FilterWrapper;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Common response format.
 * PageResponse Used for any paginated response.
 *
 * @param <T> - Indicates any type of response
 * @author Sunil Kanzar
 * @since 29th of Dec 2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -4413657906050327207L;

    private Iterable<T> content;
    private Pageable pageable;

    public static <C> PageResponse<C> of(Collection<C> data, Pageable pageable, Sort sort) {
        return new PageResponse<>(data, pageable.toBuilder().sort(sort).build());
    }

    public static <C> PageResponse<C> of(Page<C> page, Sort sort) {
        return of(page.getContent(), page.getNumber(), page.getNumberOfElements(), page.getSize(), page.getTotalPages(), page.getTotalElements(), sort);
    }

    public static <C> PageResponse<C> of(Iterable<C> content, Integer currentPage, Integer numberOfElement, Integer size, Integer totalPages, Long totalElements, Sort sort) {
        PageResponse<C> page = new PageResponse<>();
        page.content = content;
        page.setPageable(new Pageable(size, totalPages, currentPage, numberOfElement, totalElements, sort));
        return page;
    }

    public static <T> PageResponse<T> getBlank(FilterWrapper filter) {
        Pageable pageable = Pageable.builder()
                .totalPages(0)
                .numberOfElements(0)
                .totalElements(0)
                .pageNumber(0)
                .pageSize(filter.getSize()).build();
        return PageResponse.of(new ArrayList<>(), pageable, filter.getSort());
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder(toBuilder = true)
    public static class Pageable implements Serializable {
        @Serial
        private static final long serialVersionUID = -4223261549345391709L;

        private int pageSize;
        private int totalPages;
        private int pageNumber;
        private int numberOfElements;
        private long totalElements;
        private Sort sort;
    }
}