package com.example.application.view;

import com.example.application.entity.User;
import com.example.application.service.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;

        createHeader();
        createDrawer();
    }

    private void createHeader() {
        User currentUser = securityService.getAuthenticatedUser();
        H3 userFullName = new H3(currentUser.getFirstname() + " " + currentUser.getLastname());
        Button logout = new Button("logout", e -> securityService.logout());
        HorizontalLayout userBlock = new HorizontalLayout(userFullName, logout);
        userBlock.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        userBlock.getStyle().set("margin-right", "10px");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), userBlock);
        header.setClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink mainLink = new RouterLink("Главная", MainView.class);
        RouterLink cargosLink = new RouterLink("Грузы", CargoView.class);
        RouterLink customersLink = new RouterLink("Клиенты", CustomerView.class);
        RouterLink companiesLink = new RouterLink("Компании-партнёры", CompanyView.class);
        RouterLink cargoStatusesLink = new RouterLink("Статусы груза", CargoStatusView.class);
        RouterLink cargoServicesLink = new RouterLink("Сервисы для груза", CargoServiceView.class);
        RouterLink carTypesLink = new RouterLink("Типы машин", CarTypesView.class);
        RouterLink itemTypes = new RouterLink("Типы предметов", ItemTypeView.class);
        RouterLink currenciesLink = new RouterLink("Валюты", CurrencyView.class);
        RouterLink usersLink = new RouterLink("Пользователи", InternalUserView.class);
        RouterLink rolesLink = new RouterLink("Роли", RoleView.class);
        mainLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                mainLink,
                cargosLink,
                customersLink,
                companiesLink,
                cargoStatusesLink,
                cargoServicesLink,
                carTypesLink,
                itemTypes,
                currenciesLink,
                usersLink,
                rolesLink
        ));
    }
}
