/**
 *  Copyright 2018 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Turn on dimmer to 30% for 15 seconds then increase to 100%
 *
 *  Author: KiMo
 */
definition(
    name: "No Blink Dimmer",
    namespace: "dragokvm",
    author: "KiMo",
    description: "Turn on dimmer to 30% for 15 seconds then increase to 100%",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/light_contact-outlet.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/light_contact-outlet@2x.png"
)

preferences {
	section("Which Dimmer?"){
		input "switch", "capability.switch"
	}
    	section("Automatically turn switch on for this long...") {
		input "minutesOn", "number", title: "On time (in minutes):", required: true
    	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"
    	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
    	initialize()
}

def initialize()
{
    turnOnSwitch_30()
}

def turnOnSwitch_30() {
	//switch.on()
	switch.setLevel('30')
	def delay = (minutesOn * 60)
	runIn(delay, turnOnSwitch_100)
}
def turnOnSwitch_100() {
	switch.setLevel('100')
}
