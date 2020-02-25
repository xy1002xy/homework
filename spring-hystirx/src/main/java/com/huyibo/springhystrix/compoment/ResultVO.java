package com.huyibo.springhystrix.compoment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by huyibo on 2020/2/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {

    private Integer code;

    private String result;

}
