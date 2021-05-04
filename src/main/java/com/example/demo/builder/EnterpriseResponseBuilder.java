package com.example.demo.builder;

import com.example.demo.request.ColumnVO;
import com.example.demo.request.EnterpriseGetRowsRequest;
import com.example.demo.response.EnterpriseGetRowsResponse;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class EnterpriseResponseBuilder {

    public static EnterpriseGetRowsResponse createResponse(
            EnterpriseGetRowsRequest request,
            List<Map<String, Object>> rows,
            Boolean success,
            Integer total,
            Map<String, List<String>> pivotValues) {

        // int currentLastRow = request.getStartRow() + rows.size();
        // int lastRow = currentLastRow <= request.getEndRow() ? currentLastRow : -1;
        int lastRow = total >0 ? total : request.getEndRow();
        List<ColumnVO> valueColumns = request.getValueCols();

        return new EnterpriseGetRowsResponse(rows, lastRow,success, getSecondaryColumns(pivotValues, valueColumns));
    }

    private static List<String> getSecondaryColumns(Map<String, List<String>> pivotValues, List<ColumnVO> valueColumns) {

        // create pairs of pivot col and pivot value i.e. (DEALTYPE,Financial), (BIDTYPE,Sell)...
        List<Set<Pair<String, String>>> pivotPairs = pivotValues.entrySet().stream()
                .map(e -> e.getValue().stream()
                        .map(pivotValue -> Pair.of(e.getKey(), pivotValue))
                        .collect(toCollection(LinkedHashSet::new)))
                .collect(toList());

        return Sets.cartesianProduct(pivotPairs)
        .stream()
        .flatMap(pairs -> {
            // collect pivot cols, i.e. Financial_Sell
            String pivotCol = pairs.stream()
                    .map(Pair::getRight)
                    .collect(joining("_"));

            // append value cols, i.e. Financial_Sell_CURRENTVALUE, Financial_Sell_PREVIOUSVALUE
            return valueColumns.stream()
                    .map(valueCol -> pivotCol + "_" + valueCol.getField());
        })
        .collect(toList());
    }
}