require 'thor'
require 'rbconfig'
is_windows = (RbConfig::CONFIG['host_os'] =~ /mswin|mingw|cygwin/)

if is_windows
    $ant = "ant.bat"
else
    $ant = "ant"
end

class Android < Thor
    @@package = "com.arvola.android.dbtest"
    class_option :device

    desc "uninstall", "Uninstall app"

    def uninstall
        init options[:device]
        system "#{@adb} uninstall #{@@package}"
    end

    desc "login", "Log in using predefined credentials"

    def login username = "test@arvola.com", password = "makkara"
        init options[:device]
        system "#{@adb} shell input text #{username}"
        system "#{@adb} shell input keyevent 66" # Enter key is "next"
        system "#{@adb} shell input text #{password}"
        system "#{@adb} shell input keyevent 66" # Enter is also "go"
    end

    desc "type", "Type the arguments through ADB"

    def type *args
        init options[:device]
        system "#{@adb} shell input text #{args.join(" ")}"
    end

    desc "db", "Pull the app database"

    def db file = "database.db"
        init options[:device]
        system "#{@adb} pull /data/data/#{@@package}/databases/#{file}"
    end

    no_tasks do
        def init device = nil
            @adb = "adb"

            unless device.nil?
                devices = `adb devices`
                found   = false
                devices.scan(/^([^\s]+)\s+device\s*$/) do |val|
                    if val[0].start_with?(device)
                        @adb  += " -s #{val[0]}"
                        found = true
                        break
                    end
                end
                unless found
                    puts "Device not found"
                    exit
                end
            end
        end
    end
end