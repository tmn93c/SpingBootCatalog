package com.example.demo.interviews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.model.RoleModel;
import com.example.demo.model.TradeModel;
import com.example.demo.service.TradeService;
import com.google.common.collect.Lists;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;

import one.util.streamex.StreamEx;


public class SplitMultiListWithSizeInProject {
	public static <T> List<List<T>> chunk(List<T> input, int chunkSize) {
	
		int inputSize = input.size();
		int chunkCount = (int) Math.ceil(inputSize / (double) chunkSize);

		Map<Integer, List<T>> map = new HashMap<>(chunkCount);
		List<List<T>> chunks = new ArrayList<>(chunkCount);

		for (int i = 0; i < inputSize; i++) {

			map.computeIfAbsent(i / chunkSize, (ignore) -> {

				List<T> chunk = new ArrayList<>();
				chunks.add(chunk);
				return chunk;

			}).add(input.get(i));
		}

		return chunks;
	}

    // Tạo 50 thread mỗi thread loop 200K in ra 10 triệu;
	// static int count = 0; yêu cầu thread chạy song song
    static int count = 0;
	@Autowired
	private static TradeService tradeService;
	public static void main(String[] args) throws InterruptedException, IOException {
		List<RoleModel> ts  = new ArrayList<>();
		for(int i = 1; i <= 20000; i++ ){
			RoleModel t = new RoleModel();
			t.setId(Long.valueOf(i));
			t.setName("name " + i);
			t.setCode("code " + i);
			// System.out.print("name " + i);
			ts.add(t);
		}
		List<Long> tsfilter  = new ArrayList<>();
		for(int i = 1; i <= 5000; i++ ){
			Long filter = Long.valueOf(i);
			tsfilter.add(filter);
		}
		List<String> codefilter  = new ArrayList<>();
		for(int i = 1; i <= 3000; i++ ){
			String filter = "code " + i;
			codefilter.add(filter);
		}
		List<List<RoleModel>> subSets = ListUtils.partition(ts, 5);
		// System.out.print(subSets);	
		List<List<RoleModel>> chunks = chunk(ts, 5);
		for(List<RoleModel> subSet : subSets) {
			// System.out.print(subSet);	
		}
		// System.out.print(chunks);	
		List<RoleModel> customersWithMoreThan1Points = ts
		.stream()
		.filter(item -> tsfilter.contains(item.getId()))
		.filter(item -> codefilter.contains(item.getCode()))
		.toList();
		// System.out.print(customersWithMoreThan1Points);
		Set<Long> t = StreamEx.of(ts)
						.filter(m -> tsfilter.contains(m.getId()))
						.map(e -> e.getId())
						.toSet();
		// System.out.print(customersWithMoreThan1Points);

		StreamEx.ofSubLists(ts, 15000).forEach(sublist -> {
			// System.out.print(sublist);
		});
		List<RoleModel> tsEmpty  = null;
		// dont use that case to partition list
		List<List<RoleModel>> partitions = Lists.partition(ts, 5000);
		List<RoleModel> pt = partitions.stream().map(list -> {
			return list;
		}).toList()
		.stream()
		.flatMap(List::stream)
		.toList();
		// use case to partition list
		// https://stackoverflow.com/questions/32434592/partition-a-java-8-stream
		List<RoleModel> ls = new ArrayList<>();
		StreamEx.ofSubLists(ts, 25000).forEach(ls::addAll);
		// System.out.print(ls);
		List<String> testList = new ArrayList<>();
        // System.out.print(testList);

		Map<Integer, Object> map = new HashMap<>();
        map.put(1, ts);
		map.put(2, "test");
        String result = map.entrySet()
            .stream()
            .map(entry -> entry.getKey() + " - " + entry.getValue())
            .collect(Collectors.joining(", "));
		List<TradeModel> test= tradeService.getTrades();
		System.out.println("test");
	}

} 