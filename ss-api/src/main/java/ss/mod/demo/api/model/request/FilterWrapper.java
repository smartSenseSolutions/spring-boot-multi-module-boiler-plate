/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.model.request;

import com.smartsensesolutions.java.commons.FilterRequest;
import com.smartsensesolutions.java.commons.criteria.CriteriaOperator;
import com.smartsensesolutions.java.commons.filter.FilterCriteria;
import com.smartsensesolutions.java.commons.operator.Operator;
import com.smartsensesolutions.java.commons.sort.Sort;
import com.smartsensesolutions.java.commons.sort.SortType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;
import ss.mod.demo.api.model.BaseModel;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter request Wrapper with some extra function on base {@link FilterRequest}
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Setter
@Getter
public class FilterWrapper extends FilterRequest implements BaseModel {
    @Serial
    private static final long serialVersionUID = 2669549617164416862L;

    private List<FilterCriteria> orCriteria;


    public void appendSortField(String... fields) {
        if (getSort() != null) {
            String fieldsStr = "";
            for (String field : fields) {
                fieldsStr = fieldsStr + "," + field;
            }
            getSort().setColumn(getSort().getColumn() + fieldsStr);
        }
    }

    /**
     * This method copies page, size and sort object from the argument
     *
     * @param filter Source object
     * @return return new object with value of passed reference
     */
    public static FilterRequest metadataOf(FilterRequest filter) {
        FilterRequest newFilter = new FilterRequest();
        newFilter.setPage(filter.getPage());
        newFilter.setSize(filter.getSize());
        newFilter.setSort(filter.getSort());
        return newFilter;
    }

    public static FilterRequest of(int page, int size) {
        FilterRequest newFilter = new FilterRequest();
        newFilter.setPage(page);
        newFilter.setSize(size);
        return newFilter;
    }

    /**
     * This method copies page, size, sort and orCriteria object from the source with <code>CriteriaOperator</code> as
     * <code>OR</code>
     *
     * @param filter Source object
     * @return return new object with value of passed reference
     */
    public static FilterRequest orOf(FilterWrapper filter) {
        FilterRequest newFilter = metadataOf(filter);
        newFilter.setCriteria(filter.getOrCriteria());
        newFilter.setCriteriaOperator(CriteriaOperator.OR);
        return newFilter;
    }

    public static Sort toSort(String column, SortType sortType) {
        Sort sort = new Sort();
        sort.setColumn(column);
        sort.setSortType(sortType);
        return sort;
    }

    public boolean containsColumn(String column) {
        if (CollectionUtils.isEmpty(getCriteria())) {
            return false;
        }
        for (FilterCriteria criterion : getCriteria()) {
            if (criterion.getColumn().equals(column)) {
                return true;
            }
        }
        return false;
    }

    public List<String> valueList(String column, Operator operator) {
        if (CollectionUtils.isEmpty(getCriteria())) {
            return null;
        }
        for (FilterCriteria criterion : getCriteria()) {
            if (criterion.getColumn().equals(column) && criterion.getOperator().equals(operator)) {
                return criterion.getValues();
            }
        }
        return null;
    }

    public void removeCriteria(String fieldName) {
        getCriteria().removeIf(next -> next.getColumn().equals(fieldName));
    }

    public void appendOrCriteria(String fieldName, Operator operator, List<String> recordType) {
        List<FilterCriteria> filterCriteria = getOrCriteria() != null ? getOrCriteria() : new ArrayList<>();
        filterCriteria.add(new FilterCriteria(fieldName, operator, recordType));
        setOrCriteria(filterCriteria);
    }
}
