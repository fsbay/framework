/*
 * package com.cmb.edu.facade.impl;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.cmb.common.entity.BaseResult; import
 * com.cmb.common.vo.BaseFacadeVo; import com.cmb.edu.facade.UserFacade; import
 * com.cmb.edu.facade.vo.UserFacadeVo; import com.cmb.edu.service.IUserService;
 * 
 * @RestController public class UserFacadeImpl implements UserFacade{
 * 
 * private static final Logger LOGGER =
 * LoggerFactory.getLogger(UserFacadeImpl.class);
 * 
 * @Autowired private IUserService userService;
 * 
 * 
 * @Override public BaseResult<UserFacadeVo> addUser(@RequestBody
 * BaseFacadeVo<UserFacadeVo> entity) { LOGGER.info("addUser"); return new
 * BaseResult<>(entity.getData()); }
 * 
 * 
 * 
 * 
 * @Override
 * 
 * @RequestMapping(value = "facade/edu/loadUser", method = RequestMethod.POST)
 * public BaseResult<UserFacadeVo> loadUser(BaseFacadeVo<UserFacadeVo> entity) {
 * LOGGER.info("loadUser"); return new BaseResult<>(entity.getData()); }
 * 
 * }
 */