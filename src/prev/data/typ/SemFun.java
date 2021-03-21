package prev.data.typ;

import prev.common.logger.Logger;

import java.util.Collection;

public class SemFun extends SemType{

    /** Component types. */
    private final SemType[] parameters;
    public final SemType type;

    public SemFun(Collection<SemType> compTypes, SemType type) {
        this.parameters= new SemType[compTypes.size()];
        int index = 0;
        for (SemType compType : compTypes) {
            this.parameters[index++] = compType;
        }
        this.type = type;
    }

    /**
     * Returns the type of the specified component.
     *
     * @param idx The index of the component
     * @return The type of the component.
     */
    public SemType compType(int idx) {
        return parameters[idx];
    }

    /**
     * Returns the number of components.
     *
     * @return The number of components.
     */
    public int numComps() {
        return parameters.length;
    }

    @Override
    public long size() {
        long size = 0;
        for (int index = 0; index < parameters.length; index++) {
            size += parameters[index].size();
        }
        return size;
    }

    @Override
    public void log(Logger logger) {
        if (logger == null)
            return;
        logger.begElement("semtype");
        logger.addAttribute("type", "FUN");
        for (SemType compType : parameters)
            compType.log(logger);
        logger.endElement();
    }
}
