package peaksoft.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.StopListRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.StopListResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;
import peaksoft.exeption.BadRequestException;
import peaksoft.exeption.NotFoundException;
import peaksoft.repositories.MenuItemRepository;
import peaksoft.repositories.StopListRepository;
import peaksoft.services.StopListService;

import java.util.List;

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
        if (!menuItemRepository.existsById(menuItemId)){
            throw new NotFoundException(String.format("Menu with Id: %d doesnt exist", menuItemId));
        }


        List<StopList> all = stopListRepository.findAll();
        for (StopList stopList : all) {
            if (stopList.getDate().equals(request.date()))
                throw new BadRequestException("Бир дата менен бир тамак бир эле жолу сакталыш керек");
        }

        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() -> new NotFoundException(String.format("Menu with id: %d doesn't exist", menuItemId)));

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
        if (!stopListRepository.existsById(id)){
            throw new NotFoundException(String.format("Stop list with id: %d doesn't exist", id));
        }
        return stopListRepository.findByIdStopList(id);
    }

    @Override
    public SimpleResponse update(Long id, StopListRequest request) {
        if (!stopListRepository.existsById(id)){
            throw new NotFoundException(String.format("Stop list with id: %d doesn't exist", id));
        }

        StopList stopList = stopListRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Stop List with id: %d doesn't exist", id)));
        List<StopList> all = stopListRepository.findAll();
        for (StopList stopList1 : all) {
            if (stopList1.getDate().equals(request.date()))
                throw new BadRequestException("Бир дата менен бир тамак бир эле жолу сакталыш керек");
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
        if (!menuItemRepository.existsById(menuId)){
            throw new NotFoundException(String.format("Menu with id: %d doesn't exist", menuId));
        }

        if (!stopListRepository.existsById(stopListId)){
            throw new NotFoundException(String.format("Stop list with id: %d doesn't exist", stopListId));
        }

        MenuItem menuItem = menuItemRepository.findById(menuId).orElseThrow(() -> new NotFoundException(String.format("Menu with id: %d doesn't exist", menuId)));
        StopList stopList = stopListRepository.findById(stopListId).orElseThrow(() -> new NotFoundException(String.format("Stop list with id: %d doesn't exist", stopListId)));
        if (stopList.getMenuItem().getId().equals(menuItem.getId())){
            menuItem.setStopList(null);
            menuItemRepository.save(menuItem);
            stopListRepository.delete(stopList);
        }else {
                throw new BadRequestException("Not found");
            }

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Stop List with id: %d successfully DELETED", stopListId)).build();
    }



}
