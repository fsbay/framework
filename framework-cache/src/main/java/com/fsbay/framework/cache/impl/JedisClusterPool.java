/**
 * 
 */
package com.fsbay.framework.cache.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * 
 *
 * @author dengzhineng
 * @mail zhineng.deng@163.com
 * @date: 2019年6月14日 上午10:58:29
 * @version 1.0
 * @since JDK 1.8
 */
public class JedisClusterPool {
    private JedisCluster jedisCluster;

    public JedisClusterPool(String clusterNodes, int timeout, int maxRedirections, final GenericObjectPoolConfig poolConfig,
            String password) {
        Set<HostAndPort> nodes = getNodes(clusterNodes);
        if (StringUtils.isBlank(password)) {
            jedisCluster = new JedisCluster(nodes, timeout, maxRedirections, poolConfig);
        } else {
            jedisCluster = new JedisCluster(nodes, timeout, timeout, maxRedirections, password, poolConfig);
        }
    }

    private Set<HostAndPort> getNodes(String clusterNodes) {
        String[] nodes = StringUtils.split(clusterNodes, ',');
        Set<HostAndPort> set = new HashSet<HostAndPort>();
        HostAndPort hostAndPort;
        String[] host;
        for (String node : nodes) {
            host = StringUtils.split(node, ':');
            hostAndPort = new HostAndPort(host[0], Integer.valueOf(host[1]));
            set.add(hostAndPort);
        }
        return set;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void close() throws IOException {
        jedisCluster.close();
    }
}
