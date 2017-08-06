package demo.yc.lib.netstatus;

/**
 * 使用观察者模式，监听网络的变化。这个只是抽象观察者
 * 具体的观察者会实现这个接口
 */
public interface NetChangeObserver
{
    /**
     * 网络连接成功时
     */
    void onNetConnected(NetUtils.NetType type);

    /**
     * 网络连接失败时
     */
    void onNetDisConnected();

}
