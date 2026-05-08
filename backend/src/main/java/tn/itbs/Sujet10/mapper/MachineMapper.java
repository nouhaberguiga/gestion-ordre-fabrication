package tn.itbs.Sujet10.mapper;

import org.springframework.stereotype.Component;
import tn.itbs.Sujet10.dto.MachineDTO;
import tn.itbs.Sujet10.entity.Machine;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MachineMapper {

    public MachineDTO toDto(Machine machine) {
        if (machine == null) return null;
        MachineDTO dto = new MachineDTO();
        dto.setId(machine.getId());
        dto.setName(machine.getName());
        dto.setStatus(machine.getStatus());
        dto.setLastMaintenance(machine.getLastMaintenance());
        return dto;
    }

    public Machine fromDto(MachineDTO dto) {
        if (dto == null) return null;
        Machine machine = new Machine();
        machine.setId(dto.getId());
        machine.setName(dto.getName());
        machine.setStatus(dto.getStatus());
        machine.setLastMaintenance(dto.getLastMaintenance());
        return machine;
    }

    public List<MachineDTO> toListDto(List<Machine> machines) {
        return machines.stream().map(this::toDto).collect(Collectors.toList());
    }
}