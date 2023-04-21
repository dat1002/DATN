package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.models.BillDetail;
import com.fruits.congtyhoaqua.repositories.BillRepository;
import com.fruits.congtyhoaqua.services.IBillService;
import com.fruits.congtyhoaqua.services.IStatisticalService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class StatisticalServiceImp implements IStatisticalService {

    @Autowired
    private IBillService billService;

    @Autowired
    private BillRepository billRepository;


    public Double RevenueForTheMonth(String start, String end) {
        LocalDate startDateConvert = LocalDate.parse(start);
        LocalDate endDateConvert = LocalDate.parse(end);
        Double billPrice = 0.0;
        Double billDetailPrice = 0.0;
        Set<Bill> bills = new HashSet<>(billRepository.findAllByDateCreatedBetween(startDateConvert, endDateConvert));
        for (Bill bill : bills) {
            Set<BillDetail> billDetails = bill.getBillDetails();
            for (BillDetail billDetail : billDetails) {
                billDetailPrice = (double) (billDetail.getFruit().getPriceOut() * billDetail.getAmount());
                billPrice += billDetailPrice;
            }
        }
        return billPrice;
    }

    @Override
    public List<Double> RevenueForTheYear() {
        List<Double> doubles = new ArrayList<>();
        Double jan = RevenueForTheMonth("2022-01-01", "2022-01-31");
        Double feb = RevenueForTheMonth("2022-02-01", "2022-02-28");
        Double mar = RevenueForTheMonth("2022-03-01", "2022-03-31");
        Double apr = RevenueForTheMonth("2022-04-01", "2022-04-30");
        Double may = RevenueForTheMonth("2022-05-01", "2022-05-31");
        Double jun = RevenueForTheMonth("2022-06-01", "2022-06-30");
        Double jul = RevenueForTheMonth("2022-07-01", "2022-07-31");
        Double aug = RevenueForTheMonth("2022-08-01", "2022-08-31");
        Double sep = RevenueForTheMonth("2022-09-01", "2022-09-30");
        Double oct = RevenueForTheMonth("2022-10-01", "2022-10-31");
        Double nov = RevenueForTheMonth("2022-11-01", "2022-11-30");
        Double dec = RevenueForTheMonth("2022-12-01", "2022-12-31");
        doubles.add(jan);
        doubles.add(feb);
        doubles.add(mar);
        doubles.add(apr);
        doubles.add(may);
        doubles.add(jun);
        doubles.add(jul);
        doubles.add(aug);
        doubles.add(sep);
        doubles.add(oct);
        doubles.add(nov);
        doubles.add(dec);
        System.out.println(doubles.size());
        return doubles;
    }

    @Override
    public Map<String, Integer> topFiveStaffAscending(String start, String end) {
        LocalDate startDateConvert = LocalDate.parse(start);
        LocalDate endDateConvert = LocalDate.parse(end);
        Set<Bill> bills = new HashSet<>(billRepository.findAllByDateCreatedBetween(startDateConvert, endDateConvert));
        String key;
        Integer value;
        Map<String, Integer> map = new HashMap<>();
        for (Bill bill :
                bills) {
            key = bill.getUser().getName();
            if (map.containsKey(key)) {
                value = map.get(key);
                map.remove(key);
                map.put(key, value + 1);
            } else {
                map.put(key, new Integer(1));
            }
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                Integer i1 = o1.getValue();
                Integer i2 = o2.getValue();
                return i2 - i1;
            }
        };
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, comparator);
        Map<String, Integer> linked = new LinkedHashMap<>(list.size());
        for (Map.Entry<String, Integer> entry : list) {
            linked.put(entry.getKey(), entry.getValue());
        }
        return linked;
    }

    @Override
    public Map<String, Integer> topFiveStaffDecrease(String start, String end) {
        LocalDate startDateConvert = LocalDate.parse(start);
        LocalDate endDateConvert = LocalDate.parse(end);
        Set<Bill> bills = new HashSet<>(billRepository.findAllByDateCreatedBetween(startDateConvert, endDateConvert));
        String key;
        Integer value;
        Map<String, Integer> map = new HashMap<>();
        for (Bill bill :
                bills) {
            key = bill.getUser().getName();
            if (map.containsKey(key)) {
                value = map.get(key);
                map.remove(key);
                map.put(key, value + 1);
            } else {
                map.put(key, new Integer(1));
            }
        }
        // sắp xếp
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                Integer i1 = o1.getValue();
                Integer i2 = o2.getValue();
                return i1 - i2;
            }
        };
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, comparator);
        Map<String, Integer> linked = new LinkedHashMap<>(list.size());
        for (Map.Entry<String, Integer> entry : list) {
            linked.put(entry.getKey(), entry.getValue());
        }
        return linked;
    }

    @Override
    public Map<String, Integer> topFiveFruitBestseller(String start, String end) {
        LocalDate startDateConvert = LocalDate.parse(start);
        LocalDate endDateConvert = LocalDate.parse(end);
        Set<Bill> bills = new HashSet<>(billRepository.findAllByDateCreatedBetween(startDateConvert, endDateConvert));
        String key;
        Integer valueS = 0;
        Integer value;
        Map<String, Integer> map = new HashMap<>();
        for (Bill bill :
                bills) {
            Set<BillDetail> billDetails = bill.getBillDetails();
            for (BillDetail billDetail : billDetails) {
                key = billDetail.getFruit().getName();
                value = billDetail.getFruit().getPriceOut() * billDetail.getAmount();
                if (map.containsKey(key)){
                    value = map.get(key);
                    map.remove(key);
                    map.put(key,valueS+value);
                } else {
                    map.put(key,value);
                }
            }
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                Integer i1 = o1.getValue();
                Integer i2 = o2.getValue();
                return i2 - i1;
            }
        };
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, comparator);
        Map<String, Integer> linked = new LinkedHashMap<>(list.size());
        for (Map.Entry<String, Integer> entry : list) {
            linked.put(entry.getKey(), entry.getValue());
        }
        return linked;
    }

    @Override
    public Map<String, Integer> topFiveFruitSlowestSelling(String start, String end) {
        LocalDate startDateConvert = LocalDate.parse(start);
        LocalDate endDateConvert = LocalDate.parse(end);
        Set<Bill> bills = new HashSet<>(billRepository.findAllByDateCreatedBetween(startDateConvert, endDateConvert));
        String key;
        Integer valueS = 0;
        Integer value;
        Map<String, Integer> map = new HashMap<>();
        for (Bill bill :
                bills) {
            Set<BillDetail> billDetails = bill.getBillDetails();
            for (BillDetail billDetail : billDetails) {
                key = billDetail.getFruit().getName();
                value = billDetail.getFruit().getPriceOut() * billDetail.getAmount();
                if (map.containsKey(key)){
                    value = map.get(key);
                    map.remove(key);
                    map.put(key,valueS+value);
                } else {
                    map.put(key,value);
                }
            }
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                Integer i1 = o1.getValue();
                Integer i2 = o2.getValue();
                return i1 - i2;
            }
        };
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, comparator);
        Map<String, Integer> linked = new LinkedHashMap<>(list.size());
        for (Map.Entry<String, Integer> entry : list) {
            linked.put(entry.getKey(), entry.getValue());
        }
        return linked;
    }

    @Override
    public Map<String, Integer> topFiveFruitBestProfit(String start, String end) {
        LocalDate startDateConvert = LocalDate.parse(start);
        LocalDate endDateConvert = LocalDate.parse(end);
        Set<Bill> bills = new HashSet<>(billRepository.findAllByDateCreatedBetween(startDateConvert, endDateConvert));
        String key;
        Integer valueS = 0;
        Integer value;
        Map<String, Integer> map = new HashMap<>();
        for (Bill bill :
                bills) {
            Set<BillDetail> billDetails = bill.getBillDetails();
            for (BillDetail billDetail : billDetails) {
                key = billDetail.getFruit().getName();
                value = (billDetail.getFruit().getPriceOut()-billDetail.getFruit().getPriceIn()) * billDetail.getAmount();
                if (map.containsKey(key)){
                    value = map.get(key);
                    map.remove(key);
                    map.put(key,valueS+value);
                } else {
                    map.put(key,value);
                }
            }
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                Integer i1 = o1.getValue();
                Integer i2 = o2.getValue();
                return i2 - i1;
            }
        };
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, comparator);
        Map<String, Integer> linked = new LinkedHashMap<>(list.size());
        for (Map.Entry<String, Integer> entry : list) {
            linked.put(entry.getKey(), entry.getValue());
        }
        return linked;
    }

    @Override
    public Map<String, Integer> topFiveFruitWorstProfit(String start, String end) {
        LocalDate startDateConvert = LocalDate.parse(start);
        LocalDate endDateConvert = LocalDate.parse(end);
        Set<Bill> bills = new HashSet<>(billRepository.findAllByDateCreatedBetween(startDateConvert, endDateConvert));
        String key;
        Integer valueS = 0;
        Integer value;
        Map<String, Integer> map = new HashMap<>();
        for (Bill bill :
                bills) {
            Set<BillDetail> billDetails = bill.getBillDetails();
            for (BillDetail billDetail : billDetails) {
                key = billDetail.getFruit().getName();
                value = (billDetail.getFruit().getPriceOut()-billDetail.getFruit().getPriceIn()) * billDetail.getAmount();
                if (map.containsKey(key)){
                    value = map.get(key);
                    map.remove(key);
                    map.put(key,valueS+value);
                } else {
                    map.put(key,value);
                }
            }
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                Integer i1 = o1.getValue();
                Integer i2 = o2.getValue();
                return i1 - i2;
            }
        };
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, comparator);
        Map<String, Integer> linked = new LinkedHashMap<>(list.size());
        for (Map.Entry<String, Integer> entry : list) {
            linked.put(entry.getKey(), entry.getValue());
        }
        return linked;
    }
}
