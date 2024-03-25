import React, { useEffect } from 'react';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';

const Map = ({ foodTrucks }) => {
    useEffect(() => {
        // 创建地图并设置初始视图
        const map = L.map('map').setView([37.7749, -122.4194], 13);

        // 添加地图瓦片图层（你可以使用不同的地图提供商，如OpenStreetMap）
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);

        // 标记食品卡车的位置
        /*foodTrucks.forEach(truck => {
            const marker = L.marker([truck.latitude, truck.longitude]).addTo(map);
            marker.bindPopup(truck.name);
        });*/
    }, [foodTrucks]);

    return <div id="map" style={{ height: '400px' }}>nihao</div>
};

export default Map;
