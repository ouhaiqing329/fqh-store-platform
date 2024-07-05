package com.fqh.utils.response;

import lombok.Data;

import java.util.List;

/**
 * 角色以及功能编码集合
 *
 * @author fqh
 * @date 2023/03/30
 */
@Data
public class RoleFunctionCodeResp {

    private List<String> functionCodeList;

}
