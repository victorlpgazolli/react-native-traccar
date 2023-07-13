//
// Copyright 2016 - 2021 Anton Tananaev (anton@traccar.org)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

import Foundation

public class ProtocolFormatter: NSObject {
    
    public static func formatPostion(_ position: Position, url: String, alarm: String? = nil) -> URL? {
        var components = URLComponents(string: url)

        var query = String()
        
        if let deviceId = position.deviceId {
            query += "id=\(deviceId)&"
        }
        if let time = position.time {
            query += "timestamp=\(Int(time.timeIntervalSince1970))&"
        }
        if let latitude = position.latitude {
            query += String(format: "lat=%.06f&", latitude.doubleValue)
        }
        if let longitude = position.longitude {
            query += String(format: "lon=%.06f&", longitude.doubleValue)
        }
        if let speed = position.speed {
            query += "speed=\(speed)&"
        }
        if let course = position.course {
            query += "bearing=\(course)&"
        }
        if let altitude = position.altitude {
            query += "altitude=\(altitude)&"
        }
        if let accuracy = position.accuracy {
            query += "accuracy=\(accuracy)&"
        }
        if let battery = position.battery {
            query += "batt=\(battery)&"
        }
        if position.charging {
            query += "charge=true&"
        }
        if let alarm = alarm {
            query += "alarm=\(alarm)&"
        }
        components?.query = String(query.dropLast())

        // use queryItems for iOS 8
        return components?.url
    }

}
