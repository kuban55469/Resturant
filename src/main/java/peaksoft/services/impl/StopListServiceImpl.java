package peaksoft.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.StopListRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.StopListResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;
import peaksoft.repositories.MenuItemRepository;
import peaksoft.repositories.StopListRepository;
import peaksoft.services.StopListService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.03.2023
 */
@Service
public class StopListServiceImpl implements StopListService {
    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;

    public StopListServiceImpl(StopListRepository stopListRepository, MenuItemRepository menuItemRepository) {
        this.stopListRepository = stopListRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public SimpleResponse saveStopList(StopListRequest request, Long menuItemId) {
        List<StopList> all = stopListRepository.findAll();
        for (StopList stopList : all) {
            if (stopList.getDate().equals(request.date()))
                return SimpleResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Бир дата менен бир тамак бир эле жолу сакталыш керек").build();
        }

        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() -> new NoSuchElementException(String.format("Menu with id: %d doesn't exist", menuItemId)));

        StopList stopList = new StopList();
        stopList.setReason(request.reason());
        stopList.setDate(request.date());
        stopList.setMenuItem(menuItem);
        menuItem.setStopList(stopList);

        stopListRepository.save(stopList);
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Stop list with id: %d successfully SAVED", stopList.getId())).build();
    }

    @Override
    public List<StopListResponse> findAll() {
        return stopListRepository.findAllStopLists();
    }

    @Override
    public StopListResponse getById(Long id) {
        return stopListRepository.findByIdStopList(id);
    }

    @Override
    public SimpleResponse update(Long id, StopListRequest request) {
        StopList stopList = stopListRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("Stop List with id: %d doesn't exist", id)));
        List<StopList> all = stopListRepository.findAll();
        for (StopList stopList1 : all) {
            if (stopList1.getDate().equals(request.date()))
                return SimpleResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Бир дата менен бир тамак бир эле жолу сакталыш керек").build();
        }
        stopList.setReason(request.reason());
        stopList.setDate(request.date());
        stopListRepository.save(stopList);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Stop List with id: %d successfully UPDATED",
                        stopList.getId())).build();
    }

    @Override
    public SimpleResponse delete(Long menuId, Long stopListId) {
        MenuItem menuItem = menuItemRepository.findById(menuId).orElseThrow(() -> new NoSuchElementException("Not found"));
        StopList stopList = stopListRepository.findById(stopListId).orElseThrow(() -> new NoSuchElementException("Not found"));
        if (stopList.getMenuItem().getId().equals(menuItem.getId())){
            menuItem.setStopList(null);
            menuItemRepository.save(menuItem);
            stopListRepository.delete(stopList);
        }else {
                return SimpleResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Not found").build();
            }

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Stop List with id: %d successfully DELETED", stopListId)).build();
    }


}
