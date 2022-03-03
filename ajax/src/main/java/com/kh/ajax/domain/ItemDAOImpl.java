package com.kh.ajax.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {

  private static Map<Long, Item> store = new HashMap<>();
  private static long seq = 0L;

//  싱글톤을 여기서요?
  private static final ItemDAOImpl itemDAOImpl = new ItemDAOImpl();

//  프라이빗(싱글톤) 설정이라 객체 생성이 안 돼서 겟인스턴스로 가져옴? 맞나?
  private ItemDAOImpl(){}
  public static ItemDAOImpl getInstance() {
    return itemDAOImpl;
  }

  @Override
  public Item save(Item item) {
    item.setId(++seq);
    store.put(item.getId(), item);

    return item;
  }

  //목록
  @Override
  public List<Item> findAll() {

    return new ArrayList<>(store.values());

  }
  //아이디 조회
  @Override
  public Item findById(Long id) {

    return store.get(id);
  }

  //삭제
  @Override
  public Item delete(Long id) {

    return store.remove(id); //HashMap.remove : 삭제한 value를 반환. 대상키가 없으면 null.
  }

  //수정
  @Override
  public Item update(Long id, Item item) {
    item.setId(id);
    return store.put(id, item);  //HashMap.put : key에 대한 value를 반환. 없으면 null.
  }

//  저장서 전체 삭제
  @Override
  public void clearStore() {
    store.clear();
  }

}
