# Load Quartus II Tcl Project package
package require ::quartus::project

set need_to_close_project 0
set make_assignments 1

# Check that the right project is open
if {[is_project_open]} {
    if {[string compare $quartus(project) "logisimTopLevelShell"]} {
        puts "Project logisimTopLevelShell is not open"
        set make_assignments 0
    }
} else {
    # Only open if not already open
    if {[project_exists logisimTopLevelShell]} {
        project_open -revision logisimTopLevelShell logisimTopLevelShell
    } else {
        project_new -revision logisimTopLevelShell logisimTopLevelShell
    }
    set need_to_close_project 1
}
# Make assignments
if {$make_assignments} {

   set_global_assignment -name FAMILY "Cyclone II"
   set_global_assignment -name DEVICE EP2C20F484C8
   set_global_assignment -name DEVICE_FILTER_PACKAGE FBGA
   set_global_assignment -name DEVICE_FILTER_PIN_COUNT 484
   set_global_assignment -name RESERVE_ALL_UNUSED_PINS "AS INPUT TRI-STATED"
   set_global_assignment -name FMAX_REQUIREMENT "24 MHz "
   set_global_assignment -name RESERVE_NCEO_AFTER_CONFIGURATION "USE AS REGULAR IO"
   set_global_assignment -name CYCLONEII_RESERVE_NCEO_AFTER_CONFIGURATION "USE AS REGULAR IO"

    # Include all entities and gates


    set_global_assignment -name VERILOG_FILE "C:/Users/Timothy/Desktop/lab2/logisim/lab2//lab2/main/verilog/circuit/main.v"
    set_global_assignment -name VERILOG_FILE "C:/Users/Timothy/Desktop/lab2/logisim/lab2//lab2/main/verilog/gates/NAND_GATE.v"
    set_global_assignment -name VERILOG_FILE "C:/Users/Timothy/Desktop/lab2/logisim/lab2//lab2/main/verilog/gates/NAND_GATE_3_INPUTS.v"
    set_global_assignment -name VERILOG_FILE "C:/Users/Timothy/Desktop/lab2/logisim/lab2//lab2/main/verilog/gates/NAND_GATE_4_INPUTS.v"
    set_global_assignment -name VERILOG_FILE "C:/Users/Timothy/Desktop/lab2/logisim/lab2//lab2/main/verilog/gates/NAND_GATE_5_INPUTS.v"
    set_global_assignment -name VERILOG_FILE "C:/Users/Timothy/Desktop/lab2/logisim/lab2//lab2/main/verilog/toplevel/logisimTopLevelShell.v"

    # Map fpga_clk and ionets to fpga pins
    # Commit assignments
    export_assignments

    # Close project
    if {$need_to_close_project} {
        project_close
    }
}

