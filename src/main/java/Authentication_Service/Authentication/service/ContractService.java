package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Contract;
import Authentication_Service.Authentication.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    // Create
    public Contract createContract(Contract contract) {
        if (contractRepository.existsByNoContract(contract.getNoContract())) {
            throw new RuntimeException("Contract already exists");
        }
        return contractRepository.save(contract);
    }

    // List all
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    // Update
    public Contract updateContract(String id, Contract updatedData) {
        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        existingContract.setNoContract(updatedData.getNoContract());
        existingContract.setMecanic(updatedData.getMecanic());
        existingContract.setContractor(updatedData.getContractor());
        existingContract.setDescription(updatedData.getDescription());
        existingContract.setCompetitivebid(updatedData.getCompetitivebid());
        existingContract.setTotalbider(updatedData.getTotalbider());
        existingContract.setTypeContract(updatedData.getTypeContract());
        existingContract.setTypeProject(updatedData.getTypeProject());
        existingContract.setDatedContract(updatedData.getDatedContract());
        existingContract.setDuration(updatedData.getDuration());
        existingContract.setStarted(updatedData.getStarted());
        existingContract.setEnded(updatedData.getEnded());
        existingContract.setNameProject(updatedData.getNameProject());
        existingContract.setAmountBudget(updatedData.getAmountBudget());
        existingContract.setStatusContract(updatedData.getStatusContract());
        existingContract.setDate(updatedData.getDate());
        existingContract.setCreateBycontractor(updatedData.getCreateBycontractor());
    
        return contractRepository.save(existingContract);
    }

    // List by Id
    public Contract getContractById(String id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with ID: " + id));
    }
    
    // Delete
    public void deleteContract(String id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contract not found with ID: " + id);
        }
        contractRepository.deleteById(id);
    }
    
}
