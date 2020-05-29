package com.biz.lesson.business.system;

import static com.google.common.collect.Lists.newArrayList;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.lesson.dao.admin.MainMenuRepository;
import com.biz.lesson.dao.admin.MenuItemRepository;
import com.biz.lesson.dao.admin.ResourceRepository;
import com.biz.lesson.dao.admin.RoleRepository;
import com.biz.lesson.dao.admin.UserRepository;
import com.biz.lesson.model.admin.MainMenu;
import com.biz.lesson.model.admin.MenuItem;
import com.biz.lesson.model.admin.Resource;
import com.biz.lesson.model.admin.Role;
import com.biz.lesson.model.admin.User;

/**
 * 项目初始化数据使用
 */
@Service
public class InitManager {

    private static final Logger logger = LoggerFactory.getLogger(InitManager.class);

    @Autowired private MainMenuRepository mainMenuRepository;

    @Autowired private MenuItemRepository menuItemRepository;

    @Autowired private RoleRepository roleRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private ResourceRepository resourceRepository;

    @PostConstruct
    public void init(){
        if(userRepository.count() == 0){
            logger.info("开始初始化系统基础数据...");

            //User
            User user = new User();
            user.setUserId("admin");
            user.setName("超级管理员");
            user.setNameEn("SuperAdmin");
            user.setPassword("ceb4f32325eda6142bd65215f4c0f371");
            user = userRepository.save(user);

            //Main menu
            MainMenu mainMenu = new MainMenu();
            mainMenu.setName("系统信息");
            mainMenu.setNameEn("System Info");
            mainMenu.setCode(999);
            mainMenu = mainMenuRepository.save(mainMenu);

            //Menu Item
            MenuItem menuItemOfUser = buildMenuItem("用户管理", "User Management", "ROLE_USER;OPT_USER_LIST", 1, "/manage/users.do", mainMenu);
            menuItemOfUser = menuItemRepository.save(menuItemOfUser);
            MenuItem menuItemOfMainMenu = buildMenuItem("菜单管理", "Main Menu", "ROLE_MAINMENU;OPT_MAINMENU_LIST;ROLE_MENUITEM;ROLE_RESOURCE", 2, "/manage/mainMenus.do", mainMenu);
            menuItemOfMainMenu = menuItemRepository.save(menuItemOfMainMenu);
            MenuItem menuItemOfRole = buildMenuItem("角色管理", "Role Management", "ROLE_ROLE;OPT_ROLE_LIST", 3, "/manage/roles.do", mainMenu);
            menuItemOfRole = menuItemRepository.save(menuItemOfRole);

            //Resource
            Resource manageUser = builtResource("用户管理", "User Management", "OPT_USER_ADD;OPT_USER_EDIT;OPT_USER_DELETE;OPT_USER_RESET;OPT_USER_DETAIL",menuItemOfUser);
            manageUser = resourceRepository.save(manageUser);
            Resource checkUserDetail = builtResource("查看用户", "User Read", "OPT_USER_DETAIL",menuItemOfUser);
            checkUserDetail = resourceRepository.save(checkUserDetail);

            Resource manageMenuItem = builtResource("菜单管理", "Menu Management", "OPT_MAINMENU_ADD;OPT_MAINMENU_EDIT;OPT_MAINMENU_DELETE;OPT_MAINMENU_DETAIL;OPT_MENUITEM_ADD;OPT_MENUITEM_EDIT;OPT_MENUITEM_DELETE;OPT_MENUITEM_DETAIL;OPT_RESOURCE_ADD;OPT_RESOURCE_EDIT;OPT_RESOURCE_DELETE",menuItemOfMainMenu);
            manageMenuItem = resourceRepository.save(manageMenuItem);
            Resource checkMainMenu = builtResource("菜单查看", "Main Menu Management", "OPT_MAINMENU_DETAIL;OPT_MENUITEM_DETAIL",menuItemOfMainMenu);
            checkMainMenu = resourceRepository.save(checkMainMenu);

            Resource manageRole = builtResource("角色管理", "Role Management", "OPT_ROLE_ADD;OPT_ROLE_EDIT;OPT_ROLE_DELETE;OPT_ROLE_DETAIL",menuItemOfRole);
            manageRole = resourceRepository.save(manageRole);
            Resource checkRoleDetail = builtResource("角色查看", "Role Read", "OPT_ROLE_DETAIL",menuItemOfRole);
            checkRoleDetail = resourceRepository.save(checkRoleDetail);

            //Role
            Role role = new Role();
            role.setName("超级管理员");
            role.setNameEn("Super Admin");
            role.setDescription("拥有菜单管理，角色管理，用户管理权限");
            role.setMenuItems(newArrayList(menuItemOfUser, menuItemOfMainMenu, menuItemOfRole));
            role.setResources(newArrayList(manageUser, checkMainMenu, manageMenuItem, checkUserDetail, manageRole, checkRoleDetail));
            role = roleRepository.save(role);

            user.setRoles(newArrayList(role));
            userRepository.save(user);
            logger.info("完成初始化系统基础数据...");
        }
    }

    private MenuItem buildMenuItem(String name, String nameEn, String symbol, Integer code, String link, MainMenu mainMenu) {
        MenuItem menuItemOfRole = new MenuItem();
        menuItemOfRole.setName(name);
        menuItemOfRole.setNameEn(nameEn);
        menuItemOfRole.setSymbol(symbol);
        menuItemOfRole.setCode(code);
        menuItemOfRole.setLink(link);
        menuItemOfRole.setMainMenu(mainMenu);
        return menuItemOfRole;
    }

    private Resource builtResource(String name, String nameEn, String symbol, MenuItem menuItemOfUser) {
        Resource resource = new Resource();
        resource.setName(name);
        resource.setNameEn(nameEn);
        resource.setSymbol(symbol);
        resource.setMenuItem(menuItemOfUser);
        return resource;
    }
}
