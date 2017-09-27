package net.lightapi.portal.menu;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EntityWithIdAndVersion;
import net.lightapi.portal.menu.command.CreateMenuItemCommand;
import net.lightapi.portal.menu.command.DeleteMenuItemCommand;
import net.lightapi.portal.menu.command.MenuItemCommand;
import net.lightapi.portal.menu.command.UpdateMenuItemCommand;
import net.lightapi.portal.menu.domain.MenuItemAggregate;

import java.util.concurrent.CompletableFuture;

public class MenuItemServiceImpl implements MenuItemService {
    private AggregateRepository<MenuItemAggregate, MenuItemCommand> aggregateRepository;

    public MenuItemServiceImpl(AggregateRepository<MenuItemAggregate, MenuItemCommand> aggregateRepository) {
        this.aggregateRepository = aggregateRepository;
    }


    @Override
    public CompletableFuture<EntityWithIdAndVersion<MenuItemAggregate>> create(String data) {
        return aggregateRepository.save(new CreateMenuItemCommand(data));
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<MenuItemAggregate>> remove(String menuItemId) {
        return aggregateRepository.update(menuItemId, new DeleteMenuItemCommand());
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<MenuItemAggregate>> update(String menuItemId, String data) {
        return aggregateRepository.update(menuItemId, new UpdateMenuItemCommand(menuItemId, data));
    }

}
