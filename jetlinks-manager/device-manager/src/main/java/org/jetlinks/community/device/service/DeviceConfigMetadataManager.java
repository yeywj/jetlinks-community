package org.jetlinks.community.device.service;

import org.jetlinks.community.device.entity.DeviceInstanceEntity;
import org.jetlinks.community.device.entity.DeviceProductEntity;
import org.jetlinks.community.device.spi.DeviceConfigMetadataSupplier;
import org.jetlinks.core.message.codec.Transport;
import org.jetlinks.core.metadata.ConfigMetadata;
import org.jetlinks.core.metadata.ConfigPropertyMetadata;
import reactor.core.publisher.Flux;


/**
 * 设备配置信息管理器,用于获取产品或者设备在运行过程中所需要的配置信息。
 * <p>
 * 这些配置可以在协议包中{@link org.jetlinks.core.defaults.CompositeProtocolSupport#addConfigMetadata(Transport, ConfigMetadata)}进行定义
 * 或者通过实现接口{@link DeviceConfigMetadataSupplier}来定义
 * <p>
 * 在定义配置时,可以通过指定{@link ConfigPropertyMetadata#getScopes()}来定义配置的作用域返回。
 * <p>
 * 比如:
 * <pre>
 *
 *  new DefaultConfigMetadata()
 *      .add("apiUrl","API地址",StringType.GLOBAL,DeviceConfigScope.product) //只作用于产品配置
 *      .add("password","密码",StringType.GLOBAL,DeviceConfigScope.device); //只作用于设备配置
 *
 * </pre>
 * <p>
 * 注意：所有的配置都是保存在一起的，在定义字段时，要注意配置名冲突。
 *
 * @author zhouhao
 * @see DeviceConfigMetadataSupplier
 * @see DeviceInstanceEntity#getConfiguration()
 * @see DeviceProductEntity#getConfiguration()
 * @see org.jetlinks.core.device.DeviceOperator#getConfig(String)
 * @since 1.6
 */
public interface DeviceConfigMetadataManager {

    /**
     * 根据设备ID获取配置信息
     *
     * @param deviceId 产品ID
     * @return 配置信息
     * @see org.jetlinks.core.metadata.DeviceConfigScope#device
     */
    Flux<ConfigMetadata> getDeviceConfigMetadata(String deviceId);

    /**
     * 根据产品ID获取设备需要的配置定义信息
     *
     * @param productId 产品ID
     * @return 配置信息
     */
    Flux<ConfigMetadata> getDeviceConfigMetadataByProductId(String productId);

    /**
     * 根据产品ID获取产品所需配置信息
     *
     * @param productId 产品ID
     * @return 配置信息
     */
    Flux<ConfigMetadata> getProductConfigMetadata(String productId);


}